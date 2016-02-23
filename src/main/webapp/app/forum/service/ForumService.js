forumApp.service('ForumService', function($http, $q) {

	var forumService = this;

	forumService.forum = {
		id : 0,
		author : '',
		title : '',
		created : '',
		posts : [ {
			id : 0,
			author : '',
			text : '',
			created : ''
		} ]
	};
	
	forumService.forums = {
			list:[]
	};

//	forumService.forums = [ {
//		id : 1,
//		author : 'Tom',
//		title : 'Forum 1',
//		created : '01/01/1971',
//		posts : [ {
//			id : 1,
//			author : 'Mike',
//			text : 'Post 1',
//			created : '01/01/1971'
//		}, {
//			id : 4,
//			author : 'Sam',
//			text : 'Post 2',
//			created : '01/01/1971'
//		}, {
//			id : 5,
//			author : 'Mike',
//			text : 'Post 3',
//			created : '01/01/1971'
//		} ]
//	}, {
//		id : 2,
//		author : 'Mike',
//		title : 'Forum 2',
//		created : '01/01/1971',
//		posts : [ {
//			id : 2,
//			author : 'Mike',
//			text : 'Post 1',
//			created : '01/01/1971'
//		}, {
//			id : 6,
//			author : 'Tom',
//			text : 'Post 2',
//			created : '01/01/1971'
//		}, {
//			id : 7,
//			author : 'Tom',
//			text : 'Post 3',
//			created : '01/01/1971'
//		} ]
//	}, {
//		id : 3,
//		author : 'Sam',
//		title : 'Forum 3',
//		created : '01/01/1971',
//		posts : [ {
//			id : 3,
//			author : 'Mike',
//			text : 'Post 1',
//			created : '01/01/1971'
//		}, {
//			id : 8,
//			author : 'Mike',
//			text : 'Post 2',
//			created : '01/01/1971'
//		}, {
//			id : 9,
//			author : 'Mike',
//			text : 'Post 3',
//			created : '01/01/1971'
//		} ]
//	} ];

	forumService.getForum = function() {
		return forumService.forum;
	};
	forumService.setForum = function(data) {
		forumService.forum = data;
	};
	forumService.getForums = function() {
		return forumService.forums;
	};
	forumService.setForums = function(data) {
		forumService.forums.list = data;
	};
	
	forumService.retrieveForums = function(){
		var promise = $http.get('forum/view').then(
				function(success){
					console.log('forum retrieval success');
					return success.data;
				},
				function(error){
					console.log('forum retrieval error');
					return $q.reject(error);
		});
		return promise;
	};
	
	forumService.addForum = function(data) {
		console.log(data);
		var promise = $http.post('forum/add', data).then(
				function(success){
					return success.data;
				},
				function(error){
					return $q.reject(error);
				}
		);
		return promise;
		//forumService.forums.push(data);
	};
	forumService.addPostToForum = function(data){
		console.log("Adding post of: " + data);
		var promise = $http.post('post/add', data).then(
				function(success){
					return success.data;
				},
				function(error){
					return $q.reject(error);
				}
		);
		return promise;
	};

});