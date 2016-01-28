'use strict'

eventsApp.directive('courses', function() {
    return {
        restrict : 'E',
        replace: true,
        transclude: true,
        templateUrl: "./js/templates/courses.html"
    };
});