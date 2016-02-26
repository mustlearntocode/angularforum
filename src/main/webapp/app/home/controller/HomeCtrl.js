forumApp.controller('HomeCtrl', function(UserService, ForumService,
		PostService, $state) {

	var homeCtrl = this;

	homeCtrl.recentForums = ForumService.getRecentForums();

	homeCtrl.recentPosts = PostService.getRecentPosts();

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

});