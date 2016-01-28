'use strict';

eventsApp.controller('EventController',
    function EventController($scope,$http) {

        $scope.event = {
            name: 'ISHI Internship',
            date: '21/12/2015',
            time: '10:00 am',
            location: {
                address: 'C2, Safal Profitaire',
                city: 'Ahmedabad',
                province: 'Gujarat'
            },
            imageUrl: '/img/ishisystems.png'
        };


        $http.get('data/books.json').success(function(data) {
            $scope.event.books = data;
        });

        $http.get('data/courses.json').success(function(data) {
            $scope.event.courses = data;
        });

        $http.get('data/projects.json').success(function(data) {
            $scope.event.projects = data;
        });

        $http.get('data/scrum.json').success(function(data) {
            $scope.event.scrum = data;

            var flag = 0;

            var today = new Date().toLocaleDateString();

            for(var i=0; i<data.length; i++) {
                var obj = data[i];

                var givenDate = new Date(data[i].date).toLocaleDateString();

                if(today === givenDate) {
                    flag = 1;
                    break;
                }
            }

            if(flag == 0) {
                var today = new Date();
                var yyyy = today.getFullYear();
                var mm = (today.getMonth() + 1).toString();
                var dd = today.getDate().toString();

                var y = pad(yyyy,4);
                var m = pad(mm,2);
                var d = pad(dd,2);

                var day = y+"-"+m+"-"+d;

                $scope.event.scrum.push({"date":day, "tasks":[]});
            }
        });

        function pad(num, size) {
            var s = num+"";
            while (s.length < size) s = "0" + s;
            return s;
        }

        $scope.addTopic = function(course,newTopic) {
            if(course.topics.indexOf(newTopic) >= 0) {
                console.log("already exist!");
                return;
            }

            else if(newTopic == null) {
                console.log("null can not be added!")
            }

            else {
                course.topics.push(newTopic);
            }
        };

        $scope.addTask = function(scrum,newTask) {
            if(scrum.tasks.indexOf(newTask) >= 0) {
                console.log("already exist!");
                return;
            }

            else if(newTask == null) {
                console.log("null can not be added!")
            }

            else {
                scrum.tasks.push(newTask);
            }
        };

        $scope.checkDate = function(date) {

            var givenDate = new Date(date);
            $scope.today = new Date();

            var dge = givenDate.getDate() >= $scope.today.getDate();
            var mg = givenDate.getMonth() > $scope.today.getMonth();
            var yg = givenDate.getYear() > $scope.today.getYear();

            var me = givenDate.getMonth() == $scope.today.getMonth();
            var ye = givenDate.getYear() == $scope.today.getYear();

            if(yg || (ye && mg) || (ye && me && dge)) {
                return true;
            }
            return false;

        }

        $scope.upVoteSession = function(session) {
            session.upVoteCount++;
        };

        $scope.downVoteSession = function(session) {
            session.upVoteCount--;
        };

    }
);