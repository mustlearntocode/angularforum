forumApp.controller('HomeCtrl', function(UserService, ForumService,
		PostService, $state) {

	var homeCtrl = this;

	homeCtrl.recentForums = ForumService.getRecentForums();

	homeCtrl.recentPosts = PostService.getRecentPosts();

	homeCtrl.recentUsers = UserService.getRecentUsers();

	var recentForumPromise = ForumService.findTop5Forums();

	recentForumPromise.then(function(success) {
		ForumService.setRecentForums(success);
	}, function(error) {
		console.log('Error retrieving recent forums');
	});

	var recentPostPromise = PostService.retrieveTop5Posts();

	recentPostPromise.then(function(success) {
		PostService.setRecentPosts(success);
	}, function(error) {
		console.log('Error retrieving recent posts');
	});

	var recentUserPromise = UserService.findTop5Users();

	recentUserPromise.then(function(success) {
		UserService.setRecentUsers(success);
	}, function(error) {
		console.log('Error retrieving recent users');
	});

});