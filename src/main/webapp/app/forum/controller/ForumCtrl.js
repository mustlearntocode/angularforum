forumApp.controller('ForumCtrl', function(UserService, ForumService, $state, $stateParams, $timeout){
	
	var forumCtrl = this;
	
	forumCtrl.forums = ForumService.getForums();
	
	forumCtrl.forum = ForumService.getForum();
	forumCtrl.curUser = UserService.getUser();
	
	//console.log(forumCtrl.curUser);
	
	forumCtrl.message = "";
	
	forumCtrl.count = 0;
	
	forumCtrl.pages = [];
	
	forumCtrl.currentPage = 0;
	
	forumCtrl.pageSize = 5;
	

	
	/*
	 * Promise to get the count of total Forum objects
	 */
	var countPromise = ForumService.retrieveForumCount();
	
	countPromise.then(
			function(success){
				forumCtrl.count = success.count;
				forumCtrl.pageCount();
				console.log('Page count: ' + forumCtrl.pages);
			},function(error){
				console.log('count retrieval error');
	});
	
	/*
	 * Count function to create an array to loop over for pagination buttons.
	 */
	forumCtrl.pageCount = function(){
		var numberOfPages = 0;
		if(forumCtrl.count % forumCtrl.pageSize === 0){
			numberOfPages=forumCtrl.count/forumCtrl.pageSize;
		}else{
			numberOfPages=Math.floor(forumCtrl.count/forumCtrl.pageSize)+1;
		}
		for(var i = 0; i <numberOfPages;i++){
			forumCtrl.pages.push(i);
		}
		
	}
	
	forumCtrl.retrieveForums = function(){
		var promise = ForumService.retrievePaginatedForums(forumCtrl.currentPage,forumCtrl.pageSize);
		
		promise.then(
				function(success){
					ForumService.setForums(success);
				},
				function(error){
					console.log('Forum retrieval error');
				}
		);
	};
	
	forumCtrl.retrieveForums();
	
	forumCtrl.retrieveForumsByPage = function(page){
		forumCtrl.currentPage = page;
		var promise = ForumService.retrievePaginatedForums(page,forumCtrl.pageSize);
		
		promise.then(
				function(success){
					ForumService.setForums(success);
				},
				function(error){
					console.log('Forum retrieval error');
				}
		);
	};
	
	forumCtrl.doAdd = function(){
		
		forumCtrl.forum.author = forumCtrl.curUser.username;
		forumCtrl.forum.posts[0].author = forumCtrl.curUser.username;
		
		var addPromise = ForumService.addForum(forumCtrl.forum);
		
		addPromise.then(
				function(success){
					//console.log(success);
					forumCtrl.message = "Successfully added new forum!";
					ForumService.getForums().list.unshift(success);
				},function(error){
					console.log(error);
					forumCtrl.message = "There was an issue adding the forum.";
				}
		);
		
		forumCtrl.forum ={
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
			};;
	};
	
	console.log("ForumCtrl init");
	
	/*
	 * Commented out poller until refreshing model issue is fixed.
	 */
	var poll = function() {
		   $timeout(function() {
		       //service call to update forums 
			  var promise = ForumService.retrievePaginatedForums(forumCtrl.currentPage,forumCtrl.pageSize);
			  	
			  promise.then(function(success){
				  ForumService.setForums(success);
			  },function(error){
				  console.log('Forum retrieval error in poller');
			  })
			  
		      poll();
		   }, 1000);
		};     
	poll();
});