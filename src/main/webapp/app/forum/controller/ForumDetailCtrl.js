forumApp.controller('ForumDetailCtrl', function(UserService, ForumService, $state,
		$stateParams) {

	var forumDetail = this;

	forumDetail.post = {
		id : 0,
		author : '',
		text : '',
		created : ''
	};
	
	forumDetail.curUser = UserService.getUser();

	forumDetail.forums = ForumService.getForums();

	forumDetail.curForumId = $stateParams.forumId;
	
	console.log('ForumDetailCtrl init');
	console.log(forumDetail.curForumId);

	forumDetail.addPost = function() {
		console.log("Adding post: " + forumDetail.post);
		forumDetail.post.author = forumDetail.curUser.username;
		forumDetail.post.forumId = forumDetail.curForumId;
		var promise = ForumService.addPostToForum(forumDetail.post);
		
		promise.then(function(success){
			console.log(success);
			console.log('Added a new post successfully.')
			console.log(forumDetail.forums);
			for(var i = 0; i<forumDetail.forums.list.length; i++){
				console.log(i);
				console.log('Current forum in iteration.')
				console.log(forumDetail.forums.list[i].id);
				console.log('Current forum id on the page.')
				console.log(forumDetail.curForumId);
				if(forumDetail.forums.list[i].id==forumDetail.curForumId){
					console.log(forumDetail.forums.list[i]);
					forumDetail.forums.list[i].posts.push(success);
				}
			}
		},function(error){
			console.log(error);
			console.log('Failed to add a new post.')
		});
		
		forumDetail.post = {};
	};

});