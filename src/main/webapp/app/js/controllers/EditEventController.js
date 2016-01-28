'use strict'

eventsApp.controller('EditEventController',
    function EditEventController($scope) {
        $scope.saveEvent =  function(event, EventForm) {
            if(EventForm.$valid) {
                window.alert('Event ' + event.name +  ' Saved!');
            }
        }


        $scope.cancelEditing = function() {
            window.location = "/NewEvent.html";
        }

});