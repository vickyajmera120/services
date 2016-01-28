'use strict'

eventsApp.directive('books', function() {
    return {
        restrict : 'E',
        replace: true,
        templateUrl: "./js/templates/ebooks.html"
    }
});