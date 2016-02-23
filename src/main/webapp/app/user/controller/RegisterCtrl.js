forumApp.controller('RegisterCtrl', function(UserService, $state, $rootScope){
	
	var registerCtrl = this;
	
	registerCtrl.curUser = UserService.getUser();
	
	registerCtrl.message = UserService.getMessage();
	
	UserService.setMessage(null,"registration");
	
	console.log("Reg init");
	
	registerCtrl.doRegister = function(isValid){
		
		var promise = UserService.registerUser(registerCtrl.curUser);
		
		promise.then(function(success){
			console.log(success);
			UserService.setMessage("Successful registration!","registration");
			$rootScope.from = "registration";
			$state.go('login');
		},function(error){
			console.log(error);
			UserService.setMessage(error.data.message, "registration");
			console.log("Register CTRL message: " + registerCtrl.message);
		});
		
		
		
		
	};
	
});