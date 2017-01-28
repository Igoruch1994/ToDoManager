/**
 * Created by Igoruch on 13.01.2017.
 */
var app = angular.module("appToDo", []);

app.controller("ManagerToDoCtrl", function ($scope, $http,$window) {
    
    $scope.CurrentUserToDoGroups=null;
    $scope.CurrentToDoGroupTasks=null;
    $scope.CurrentChooseGroup=null;
    $scope.createTaskGroup=false;
    $scope.createTask=false;
    $scope.personalInfo=null;



    $scope.GetPersonalInfo=function () {
        $http({
            method: 'GET',
            url:'http://localhost:8080/user/identify',
            withCredentials: true
        }).success(function (data) {
            $scope.personalInfo=data;
            $scope.GetCurrentUserToDoGroups();
        });
    };


    $scope.GetCurrentUserToDoGroups=function () {
        $http({
            method: 'GET',
            url:'http://localhost:8080/taskGroupsByUserId/'+$scope.personalInfo.id,
            withCredentials: true
        }).success(function (data) {
            $scope.CurrentUserToDoGroups=data;
        });
    };

    $scope.GetCurrentToDoGroupTasks=function (group) {
        $scope.CurrentChooseGroup=group;
        $http({
            method: 'GET',
            url:'http://localhost:8080/tasksByTaskGroupId/'+group.id,
            withCredentials: true
        }).success(function (data) {
            $scope.CurrentToDoGroupTasks=data;
        });
    };

    $scope.createNewTaskGroup = function (Name) {
        $http({
            method: 'POST',
            url:'http://localhost:8080/addTaskGroupToUserId/'+$scope.personalInfo.id,
            withCredentials: true,
            data: {name:Name}
        }).success(function (data) {
            $scope.createTaskGroup=false;
            $window.alert("TaskGroup " + Name+" created!");
            $scope.GetCurrentUserToDoGroups();
        })
            .error(function (data, status, header, config) {
            });

    };

    $scope.createNewTasks = function (text) {
        $http({
            method: 'POST',
            url:'http://localhost:8080/addTaskToTaskGroupId/'+$scope.CurrentChooseGroup.id,
            withCredentials: true,
            data: {text:text }
        }).success(function (data) {
            $scope.createTask=false;
            $window.alert("Tasks " + text+" created!");
            $scope.GetCurrentToDoGroupTasks($scope.CurrentChooseGroup);
        })
            .error(function (data, status, header, config) {
            });
    };

    $scope.changeStatusOnTrue = function (taskId) {
        $http({
            method: 'POST',
            url:'http://localhost:8080/updateTask/'+taskId,
            withCredentials: true,
            data: {status:true}
        }).success(function (data) {
            $window.alert("Done,very good!");
            $scope.GetCurrentToDoGroupTasks($scope.CurrentChooseGroup);
        })
            .error(function (data, status, header, config) {
            });
    };

    $scope.changeStatusOnFalse = function (taskId) {
        $http({
            method: 'POST',
            url:'http://localhost:8080/updateTask/'+taskId,
            withCredentials: true,
            data: {status:false}
        }).success(function (data) {
            $window.alert("You must do it!");
            $scope.GetCurrentToDoGroupTasks($scope.CurrentChooseGroup);
        })
            .error(function (data, status, header, config) {
            });
    };

    $scope.DeleteTask = function (taskId) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteTask/'+taskId,
        }).success(function (data) {
            $window.alert("Deleted task!");
            $scope.GetCurrentToDoGroupTasks($scope.CurrentChooseGroup);
        })
            .error(function (data, status, header, config) {
            });
    };

    $scope.DeleteTaskGroups = function (taskGroupId) {
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteTaskGroup/'+taskGroupId,
        }).success(function (data) {
            $window.alert("Deleted taskGroup!");
            $scope.GetCurrentUserToDoGroups();
            $scope.CurrentChooseGroup=null;
        })
            .error(function (data, status, header, config) {
            });
    };

    $scope.logout=function () {
        $window.location.href = 'http://localhost:8080/logout';
    }

});