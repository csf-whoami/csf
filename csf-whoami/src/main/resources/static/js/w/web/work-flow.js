$("#parent-menu").addClass("open");
$("#child-menu").addClass("active");
let findGroupByName = "/w/api/v1/workflow/findGroup";
let foundGroup      = "/w/group-info/";
let notFoundGroup   = "/w/group-not-found.html?";

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