'use strict'

eventsApp.directive('addScrum', function() {
    return {
        restrict : 'E',
        replace: true,
        transclude: true,
        template: "<div ng-show='visible' class='span8'> <input type='text' id='addt' ng-model='newTopic' placeholder='Add topics...'> <button class='btn' style='margin-bottom:9px' ng-click='addTopic(course,newTopic)'>Add</button> </div>",


        controller: function($scope) {
            $scope.visible = true;

            alert(scrum.date);

            $scope.toggleVisibility = function() {
                $scope.visible = !$scope.visible;
            }
        }
    };
});