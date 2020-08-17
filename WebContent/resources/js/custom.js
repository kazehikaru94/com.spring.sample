$(document).ready(function() {
  $('.delete-user').click(function(event) {
	event.preventDefault();
	event.stopPropagation();
	var id = $(this).data('id');
    $.ajax({
	  type : 'DELETE',
	  contentType : "application/json",
	  url : $(this).attr('href'),
	  dataType : 'json',				
	  success : function(data) {
		var response = JSON.parse(data);
		if(response.result == 'OK') {
		  $('#user_' + id).remove();
		}
	  }
	});
  });
});