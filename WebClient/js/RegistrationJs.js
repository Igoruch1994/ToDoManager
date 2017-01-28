/**
 * Created by Igoruch on 13.01.2017.
 */
var app = angular.module("app", []);



app.controller("RegistrationCtrl", function ($scope, $http,$window) {

    $scope.user = {

    };

    $scope.hrefOnLogIn=function () {
        $window.location.href = 'http://localhost:8080/login';
    };

    $scope.registration=function () {
            $scope.user.login=$scope.login;
            $scope.user.password=$scope.password;
            $scope.user.email=$scope.email;
        $http.post('http://localhost:8080/registrateUser', $scope.user)
            .success(function (data, status, headers, config) {
                $scope.RegistrData=data;
            })
            .error(function (data, status, header, config) {
                $scope.RegistrData = data;
            });
    }
});