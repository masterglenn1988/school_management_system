$('#login-btn').click(function(){
    login();
});

function login(){
    let email = $("#email").val();
    let password = $("#password ").val();

    if(email !== "" && password !== ""){
        let loginDto = {
            "username" : email,
            "password" : password
        };
        console.log(loginDto);
        authenticateUserCredentials(loginDto);
    }
}

function authenticateUserCredentials(v_data){
    let baseUrl = window.location.origin;
    if(v_data !== null){
        $.ajax({
            url: baseUrl + "/api/v1/auth",
            type: 'POST',
            dataType : 'json',
            contentType: 'application/json',
            data: JSON.stringify(v_data),
            success : function(result) {
                if(result.status === 200){
                    console.log(result.message);
                    console.log(result.message[0].token);
                    f_setTkn("tkn", result.message[0].token);
                    // f_checkTokenValidity(endpoint.eds_token_validity, f_getTkn("tkn"), endpoint.eds_ui_dashboard);
                }else {
                    $('#errormsg').html(result.desc);
                }
            }
        });
    }
}

(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()


// $(document).ready(function() {
// 	var backgrounds = [
// 	  'resources/images/bg-login/bg-1.jpg',
// 	  'resources/images/bg-login/bg-2.jpg',
// 	  'resources/images/bg-login/bg-3.jpg',
// 	  'resources/images/bg-login/bg-4.jpg',
// 	  'resources/images/bg-login/bg-5.jpg',
// 	  'resources/images/bg-login/bg-6.jpg',
// 	  'resources/images/bg-login/bg-7.jpg',
// 	  'resources/images/bg-login/bg-8.jpg',
// 	  'resources/images/bg-login/bg-9.jpg',
// 	  'resources/images/bg-login/bg-10.jpg',
// 	  'resources/images/bg-login/bg-11.jpg'
// 	];

//    function setBackgroundImage() {
// 	  if ($(window).width() >= 576) {
// 		var randomBackground = backgrounds[Math.floor(Math.random() * backgrounds.length)];
// 		$('body').addClass('with-background').css('background-image', 'url(' + randomBackground + ')');
// 	  } else {
// 		$('body').removeClass('with-background').css('background-image', 'none');
// 	  }
// 	}

// 	setBackgroundImage();

// 	$(window).resize(function() {
// 	  setBackgroundImage();
// 	});
//   });


disableGoBack();