package com.jefrido.randomuser.service;

import com.jefrido.randomuser.domain.ApiResponse;
import com.jefrido.randomuser.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${randomuser.api.url}")
    private final String API_URL;

    private final RestTemplate restTemplate;


    /**
     * Fetches users from randomuser.me API.
     *
     * @param results the number of users to fetch
     * @param sortBy  the attribute to sort by
     * @return List of {@link User}.
     */
    @Override
    public List<User> getUsers(int results, String sortBy) {
        List<User> randomUsers = Collections.emptyList();

        try {
            // Fetch users from randomuser.me API
            String url = API_URL + "&results=" + results;
            ApiResponse apiResponse = restTemplate.getForObject(url, ApiResponse.class);

            if (apiResponse != null && apiResponse.getResults() != null && !apiResponse.getResults().isEmpty()) {
                randomUsers = apiResponse.getResults();

                // Format the date of birth
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                // Sort the users
                randomUsers = randomUsers.stream()
                        .peek(user -> {
                            // Get the date of birth
                            String birthDateStr = user.getDob().getDate();
                            LocalDate birthDate = LocalDate.parse(birthDateStr, inputFormatter);

                            // Format the date of birth
                            String birthDateFormatted = birthDate.format(outputFormatter);
                            user.getDob().setDate(birthDateFormatted);
                        })
                        .sorted((u1, u2) -> {
                            if ("dob".equals(sortBy)) {
                                LocalDate dob1 = LocalDate.parse(u1.getDob().getDate(), outputFormatter);
                                LocalDate dob2 = LocalDate.parse(u2.getDob().getDate(), outputFormatter);
                                return dob1.compareTo(dob2);
                            } else if ("name".equals(sortBy)) {
                                String name1 = u1.getName().getFirst() + " " + u1.getName().getLast();
                                String name2 = u2.getName().getFirst() + " " + u2.getName().getLast();
                                return name1.compareToIgnoreCase(name2);
                            }
                            return 0;
                        })
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            log.error("Error while fetching users from randomuser.me", e);
        }

        return randomUsers;
    }
}
