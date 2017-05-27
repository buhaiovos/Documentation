$(document).ready(function() {
        $('#GroupTableContainer').jtable({
            title : 'Академічні групи',
            editinline: { enable : true },
            actions : {
                listAction : 'AcademicGroupController?action=list',
                createAction : 'AcademicGroupController?action=create',
                updateAction : 'AcademicGroupController?action=update',
                deleteAction : 'AcademicGroupController?action=delete'
            },
            fields : {
                id : {
                    title : 'Ідентифікатор',
                    key : true,
                    list : false,
                    create : false,
                    edit : false        
                },
                cipher : {
                    title : 'Шифр',
                    width : '10%',
                    edit : true
                },
                budgetaryStudents : {
                    title : 'Студенти бюджетної форми',
                    width : '10%',
                    edit : true
                },
                contractStudents : {
                    title : 'Студенти контрактної форми',
                    width : '10%',
                    edit : true
                },
                startYear : {
                    title : 'Рік набору',
                    width : '10%',
                    edit : true
                },
                specialization : {
                    title : 'Спеціалізація',
                    options: 'SpecializationController?action=dropdownlist',
                    width : '20%',
                    edit : true                                    
                },
                qualification : {
                    title : 'Освітньо-кваліфікаційний рівень',
                    display: function(data){
                        if(typeof data.record.qualification === 'undefined')
                            return null;
                        
                        return data.record.qualification.denotation;
                    },
                    width : '20%',
                    edit : true
                },
                educationForm : {
                    title : 'Форма навчання',
                    display: function(data){
                        if(typeof data.record.educationForm === 'undefined')
                            return null;
                        
                        return data.record.educationForm.denotation;
                    },
                    width : '10%',
                    edit : true
                },
                workplan : {
                    title : 'Робочий план',
                    display: function(data){
                        if(typeof data.record.workplan === 'undefined')
                            return null;
                        
                        return data.record.workplan.id;
                    },
                    width : '10%',
                    edit : true
                }
            }
        });
        $('#GroupTableContainer').jtable('load');
});