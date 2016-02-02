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


        $http.get('/rest/students').success(function(data) {
            $scope.event.students = data;
        });

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

            $http.post('data/scrum2.json',scrum.tasks);
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


        $scope.showDetails = function(student) {
            $('#det' + student.id).removeClass('hidden');
            $('#showDetailsBtn' + student.id).addClass('hidden');
            $('#hideDetailsBtn' + student.id).removeClass('hidden');
        }

        $scope.hideDetails = function(student) {
            $('#det' + student.id).addClass('hidden');
            $('#showDetailsBtn' + student.id).removeClass('hidden');
            $('#hideDetailsBtn' + student.id).addClass('hidden');
        }

        $scope.deleteStudent = function(student) {

            var confirmation = confirm("Are you sure you want to delete record with id=" + student.id);

            if(confirmation) {
                $http.delete('/rest/delete/' + student.id);
                $('#record' + student.id).fadeOut(500);
                console.log("Student with id " + student.id + " deleted!");
            }
        }


        $scope.Register = function() {

            var firstName = document.getElementById('first_name').value;
            var lastName = document.getElementById('last_name').value;
            var password1 = document.getElementById('password').value;
            var password2 = document.getElementById('password_confirmation').value;
            var email = document.getElementById('email').value;
            var mobile = document.getElementById('mobile').value;
            var address = document.getElementById('address').value;
            var city = document.getElementById('city').value;;
            var pincode = document.getElementById('pincode').value;
            var state = document.getElementById('state').value;
            var country = document.getElementById('country').value;

            /*
            alert(firstName + " " + lastName + " " + password1 + " " + password2 + "\n" +
                    email + " " + mobile + " " + "\n"+
                    address + "\n" +
                    city + " " + pincode + " " + state + " " + country
            );*/

            var student = {firstName: firstName, lastName: lastName, email: email, mobile: mobile, address: address,
                city: city, pincode: pincode, state:state, country:country
            }

            var student_details = JSON.stringify(student);

            $http.post('/rest/register', student_details);

            alert("registration successful...");
            
        }



        $scope.filteredStudents = {};
        $scope.currentPage = 1;
        $scope.numPerPage = 10;
        $scope.maxSize = 5;
        $scope.studentCount = 50;//$scope.numPerPage * $scope.maxSize;

        $scope.sliceObj = function(obj, start, end) {
            var sliced = {};
            var i = 0;
            for (var k in obj) {
                if (i >= start && i < end) {
                    sliced[k] = obj[k];
                }
                i++;
            }
            return sliced;
        };

        $scope.$watch('currentPage + numPerPage', function() {
            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;

            $scope.filteredStudents = $scope.sliceObj($scope.event.students, begin, end);
        });


        $scope.showStudents = function() {
            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;

            $scope.filteredStudents = $scope.sliceObj($scope.event.students, begin, end);

            if($scope.event.students) {
                $scope.studentCount = _.size($scope.event.students);
            }

            $('#studentsPage').load().fadeIn(500);
        }

    }
);