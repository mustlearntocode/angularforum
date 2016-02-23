var forumApp = angular.module('ForumApp', [ 'ui.router' ]);

forumApp.config(function($stateProvider, $urlRouterProvider) {
	
	$urlRouterProvider.otherwise('/');
	
	$stateProvider
	.state('login',{
		url: '/login',
		templateUrl : 'app/user/templates/login.html',
		controller: 'LoginCtrl as loginCtrl'
	})
	.state('register',{
		url: '/register',
		templateUrl : 'app/user/templates/register.html',
		controller: 'RegisterCtrl as registerCtrl'
	})
	.state('home',{
		url: '/',
		templateUrl : 'app/home/templates/home.html',
		controller: 'HomeCtrl as homeCtrl'
	})
	.state('forum', {
		url: '/forum',
		templateUrl : 'app/forum/templates/forumsMain.html',
		controller: 'ForumCtrl as forumCtrl'
	})
//	.state('forum.add', {
//		url: '/forumAdd',
//		templateUrl : 'app/forum/templates/forumAdd.html'
//	})
	.state('forum.detail',{
		url: '/detail/:forumId',
		templateUrl: 'app/forum/templates/forumDetail.html',
		controller: 'ForumDetailCtrl as forumDetailCtrl'
	});
	
});