forumApp.service('PostService', function($http,$q){
	
	var postService = this;
	
	postService.recentPosts = {
			list:[]
	};
	
	postService.getRecentPosts = function(){
		return postService.recentPosts;
	};
	
	postService.setRecentPosts = function(posts){
		postService.recentPosts.list = posts;
	};
	
	postService.retrieveTop5Posts = function(){
		
		var promise = $http.get('post/top').then(
			function(success){
				return success.data;
			},function(error){
				return $q.reject(error);
			}
		);
		
		return promise;
	};
	
});