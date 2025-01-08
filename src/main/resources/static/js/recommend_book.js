document.addEventListener('DOMContentLoaded', function () {
    const bookList = document.getElementById('book-list');
    const items = bookList.querySelectorAll('li');

    items.forEach(item => {
        const clone = item.cloneNode(true);
        bookList.appendChild(clone);
    });

    const totalWidth = items.length * 150 * 2;
    bookList.style.width = `${totalWidth}px`;
});