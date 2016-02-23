forumApp.controller('ForumCtrl', function(UserService, ForumService, $state, $stateParams){
	
	var forumCtrl = this;
	
	forumCtrl.forums = ForumService.getForums();
	
	forumCtrl.forum = ForumService.getForum();
	forumCtrl.curUser = UserService.getUser();
	
	console.log(forumCtrl.curUser);
	
	forumCtrl.message = "";
	
	var promise = ForumService.retrieveForums();
	
	promise.then(
			function(success){
				ForumService.setForums(success);
			},
			function(error){
				console.log('Forum retrieval error');
			}
	);
	
	forumCtrl.doAdd = function(){
		
		forumCtrl.forum.author = forumCtrl.curUser.username;
		forumCtrl.forum.posts[0].author = forumCtrl.curUser.username;
		
		var addPromise = ForumService.addForum(forumCtrl.forum);
		
		addPromise.then(
				function(success){
					console.log(success);
					forumCtrl.message = "Successfully added new forum!";
					ForumService.getForums().list.push(success);
				},function(error){
					console.log(error);
					forumCtrl.message = "There was an issue adding the forum.";
				}
		);
	};
	
	console.log("ForumCtrl init");
});