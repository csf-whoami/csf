$("#parent-menu").addClass("open");
$("#child-menu").addClass("active");
let findGroupByName     = "/w/api/v1/workflow/findGroup";

$("#searchGroup").click(function () {
	let groupName = $('input[name="groupName"]').val();
    let groupType = $('select[name="groupType"]').val();

    $.ajax({
    	  url: findGroupByName,
    	  type: "GET", //send it through get method
    	  data: { 
    	    name: groupName,
    	    type: groupType
    	  },
        success: function (data) {
            console.log("Data: ", data)
        },
        error: function (xhr) {
//            modal.openOneModalMessage("Not success");
            console.log("Not success");
        }
    });
});