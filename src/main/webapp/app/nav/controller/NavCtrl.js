/**
 * 
 */
forumApp.controller('NavCtrl', function(UserService){
	
	var nav = this;
	
	nav.currentUser = UserService.getUser();
	
	console.log("Nav ctrl init");
	
});