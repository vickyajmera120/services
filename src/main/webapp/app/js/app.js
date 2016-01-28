'use strict';

var eventsApp = angular.module('eventsApp', ['ngResource','ngRoute','ui.bootstrap'])
    .config( ['$routeProvider',
        function($routeProvider) {
            $routeProvider
                .when('/index', {
                    templateUrl: './EventDetails.html',
                    controller: 'EventController'
                })
                .when('/EventDetails', {
                    templateUrl: './EventDetails.html',
                    controller: 'EventController'
                })
                .when('/courses', {
                    templateUrl: './courses.html',
                    controller: 'EventController'
                })
                .when('/ebooks', {
                    templateUrl: './ebooks.html',
                    controller: 'EventController'
                })
                .when('/projects', {
                    templateUrl: './projects.html',
                    controller: 'EventController'
                })
                .when('/scrum', {
                    templateUrl: './scrum.html',
                    controller: 'EventController'
                })
                .when('/students', {
                    templateUrl: './students.html',
                    controller: 'EventController'
                })
                .when('/register', {
                    templateUrl: './Registration.html',
                    controller: 'EventController'
                })
                .when('/update', {
                    templateUrl: './Update.html',
                    controller: 'EventController'
                })
                .otherwise({
                    redirectTo: '/EventDetails'
                });
}]);
