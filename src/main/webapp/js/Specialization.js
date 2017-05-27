$(document).ready(function() {
        $('#SpecializationTableContainer').jtable({
            title : 'Спеціалізації',
            editinline: { enable : true },
            actions : {
                listAction : 'SpecializationController?action=list',
                createAction : 'SpecializationController?action=create',
                updateAction : 'SpecializationController?action=update',
                deleteAction : 'SpecializationController?action=delete'
            },
            fields : {
                id : {
                    title : 'Ідентифікатор',
                    key : true,
                    list : false,
                    create : false,
                    edit : false        
                },
                denotation : {
                    title : 'Назва',
                    width : '100%',
                    edit : true
                }
            }
        });
        $('#SpecializationTableContainer').jtable('load');
});