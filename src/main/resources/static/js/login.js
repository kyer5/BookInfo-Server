document.getElementById('login')
    .addEventListener('submit', async function (event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        const data = {
            email: email,
            password: password,
        };

        const response = await fetch('/user/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            console.log('서버 응답:', result);
            alert('로그인 성공');
            window.location.href = '/';
        } else {
            const errorText = await response.text();
            console.error('서버 에러:', errorText);
            alert('서버 에러 발생');
        }

    });
