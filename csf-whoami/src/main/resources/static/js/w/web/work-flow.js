$("#parent-menu").addClass("open");
$("#child-menu").addClass("active");
let findGroupByName = "/w/api/v1/workflow/findGroup";
let foundGroup      = "/w/group-info/";
let notFoundGroup   = "/w/group-not-found.html?";
let login           = "/w/api/v1/workflow/login";

$("#searchGroup").click(function() {
    let groupName = $('input[name="groupName"]').val();
    let groupType = $('select[name="groupType"]').val();

    $.ajax({
        url : findGroupByName,
        type : "GET", // send it through get method
        data : {
            name : groupName,
            type : groupType
        },
        success : function(data) {
            if(data.length > 0 && (!data.code || data.code == "")){
                // Redirect to successful.
                location.href = foundGroup+data.id+".html";
            }else{
                // Endcode
                let name = window.btoa(unescape(encodeURIComponent(groupName)));
                let type = window.btoa(unescape(encodeURIComponent(groupType)));
                location.href = notFoundGroup+"name="+name+"&type="+type;
            }
        },
        error : function(xhr) {
            // modal.openOneModalMessage("Not success");
            console.log("Not success");
        }
    });
});

function decodeParam(inputVal){
    return window.atob(inputVal);
}

$("form").submit(function(e) {
    if(false){
        modal.openOneModalMessage('Validation.');
        e.preventDefault();
    }
});

$("#submitBtn").click(function() {
    let email = $('input[name="email"]').val();
    let password = $('input[name="password"]').val();

    console.log("email: ", email);
    console.log("password: ", password);
    let formData = {
        "username": email,
        "password": password
    };

    $.ajax({
        type: "POST",
        url: login,
        contentType: false,
        async: false,
        processData: false,
        data: JSON.stringify(formData),
        success : function(data) {
            console.log("data: ", data);
        },
        error : function(xhr) {
            // modal.openOneModalMessage("Not success");
            console.log("Not success");
        }
    });
});