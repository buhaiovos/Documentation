$(document).ready(function() {
    console.log("HI");
        $('#GroupTableContainer').jtable({
                title : 'Groups List',
                editinline: { enable : true },
                actions : {
                        listAction : 'AcademicGroupController?action=list',
                        createAction : 'AcademicGroupController?action=create',
                        updateAction : 'AcademicGroupController?action=update',
                        deleteAction : 'AcademicGroupController?action=delete'
                },
                fields : {
                        id : {
                                title : 'id',
                                width : '40%',
                                key : true,
                                list : true,
                                edit : false,
                                create : true
                        },
                        cipher : {
                                title : 'cipher',
                                width : '60%',
                                edit : true
                        }
                }
        });
        $('#GroupTableContainer').jtable('load');
});