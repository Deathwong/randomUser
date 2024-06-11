function showInfo(button) {
    const index = button.getAttribute('data-index');
    const userId = button.getAttribute('data-user-id');
    const message = button.getAttribute('data-message');

    const card = button.closest('.card');
    const infoMessage = card.querySelector('.info-message');
    const infoContent = card.querySelector('.info-content');
    const infoId = `info-${index}-${userId}`;
    const infoText = document.getElementById(infoId).textContent;

    infoContent.textContent = infoText;
    infoMessage.textContent = message;

    const buttons = button.closest('.d-flex').querySelectorAll('button');
    buttons.forEach((btn, i) => {
        if (btn === button) {
            btn.classList.add('btn-outline-success');
            btn.classList.remove('btn-outline-secondary');
        } else {
            btn.classList.remove('btn-outline-success');
            btn.classList.add('btn-outline-secondary');
        }
    });
}
