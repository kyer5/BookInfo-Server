document.getElementById('dataForm')
    .addEventListener('submit', async function (event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const nickname = document.getElementById('nickname').value;
        const phone = document.getElementById('phone').value;
        const password = document.getElementById('password').value;
        const passwordCheck = document.getElementById('passwordCheck').value;

        const data = {
            email: email,
            nickname: nickname,
            phone: phone,
            password: password,
            passwordCheck: passwordCheck
        };

        const response = await fetch('/user/signup', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            const result = await response.json();
            console.log('서버 응답:', result);
            alert('가입이 완료되었습니다.');
            window.location.href = '/';
        } else {
            const errorText = await response.text();
            console.error('서버 에러:', errorText);
            alert('서버 에러 발생');
        }

    });
