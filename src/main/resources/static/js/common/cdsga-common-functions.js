function f_getTkn(v_key){
    return localStorage.getItem(v_key);
}

function f_setTkn(v_key, v_value){
    localStorage.setItem(v_key, v_value);
}

function f_checkTokenValidity(v_url1, v_tkn, v_url2){
    $.ajax({
        url: v_url1,
        type: 'GET',
        contentType: 'application/json',
        data: {"tkn" : v_tkn},
        headers: {'Authorization': v_tkn},
        success: function (result) {
            if(result.success === true){
                // $.ajax({
                //     url: $(location).attr('href', v_url2),
                //     type: GET,
                //     contentType: APP_JSON,
                //     data: {'tkn' : v_tkn},
                //     headers: {'Authorization': v_tkn}
                // });
                setTimeout(function(){
                    $(location).attr('href', v_url2);
                }, 500);
            }else{
                $('#errormsg').html(result.content);
            }
        },
        error: function(err) {
            switch (err.status) {
                case "400":
                    // bad request
                    break;
                case "401":
                    // unauthorized
                    break;
                case "403":
                    // forbidden
                    break;
                default:
                    //Something bad happened
                    break;
            }
        }
    });
}

function checkRoles(){
    let role = parseJwt(f_getTkn("tkn")).roles[0];
    if(role === 'ROLE_USER'){
        $('#admin-settings').hide();
    }
}

function strToJson(v_tkn){
    const decode = token => decodeURIComponent(atob(
        token.split('.')[1].replace('-', '+').replace('_', '/')).split('').map(c => `%${('00' + c.charCodeAt(0).toString(16)).slice(-2)}`).join(''));
    return JSON.parse(decode(sessionStorage.getItem(v_tkn)));
}

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}

function customizedMessagePrompt(message, msgPromptClass, dialog){
    if(msgPromptClass === "success"){
        customizedSuccessMessagePrompt(message);
        $("#boxBack-success").css('z-index',210000);
        $('#customized-success-msg-prompt-btn').click(function(){
            dialog.visible = false;
        });
    } else if(msgPromptClass === "error"){
        customizedErrorMessagePrompt(message);
        $("#boxBack-error").css('z-index',210000);
        $('#customized-error-msg-prompt-btn').click(function(){
            dialog.visible = false;
        });
    }else if(msgPromptClass === "confirm"){
        customizedConfirmationMessagePrompt(message);
        $("#boxBack-confirm").css('z-index',210000);
    }else if(msgPromptClass === "info"){
        customizedInformationMessagePrompt(message);
        $("#boxBack-info").css('z-index',210000);
    }
}

function disableGoBack(){
    window.history.forward();
    document.addEventListener("onkeydown", function my_onkeydown_handler(){
            switch (event.keyCode) {
                case 116 : // F5;
                    event.returnValue = false;
                    event.keyCode = 0;
                    window.status = "We have disabled F5";
                    break;
            }
        }
    );
}