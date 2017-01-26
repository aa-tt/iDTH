<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>
<link href="/dth-rest/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/dth-rest/resources/js/ext-all-debug.js"></script>
<script>
	Ext.onReady(function () {
		Ext.create("Ext.form.Label", {
			text : "Hello World All set in Label!!",
			renderTo : Ext.getBody()
		}),
		Ext.create("Ext.Button", {
			text : "OK Button",
			renderTo : Ext.getBody(),
			handler : function() {
				Ext.Msg.alert("Hello World","All set Alert box!!!");
			}
		}),
		Ext.create("Ext.form.field.Text", {
			fieldLabel : "Name",
			renderTo : Ext.getBody(),
			id : "nametext"
		}),
		Ext.create("Ext.Button", {
			text : "Click",
			renderTo : Ext.getBody(),
			handler : function() {
				Ext.Msg.alert("Hello", Ext.getCmp("nametext").getValue());
			}
		}),
		Ext.create('Ext.data.Store', {
		    storeId:'dthCustomerStore',
		    autoLoad: true,
		    //fields:['customerName', 'contactNo', 'emailId'],
		    data:{'items':[
		        { 'customerName': 'Lisa',  "contactNo":"555-111-1224",  "emailId":"lisa@simpsons.com" },
		        { 'customerName': 'Bart',  "contactNo":"555-222-1234",  "emailId":"bart@simpsons.com" }
		    ]},
		    proxy: {
		        //type: 'memory',
		        type: 'ajax',
		        api: {
		        	read: '/dth-rest/customer/1'
		        },
		        reader: {
		            type: 'json',
		            //rootProperty: 'items'
		        }
		    }
		}),
		Ext.create('Ext.grid.Panel', {
		    title: 'Customer Details',
		    store: Ext.data.StoreManager.lookup('dthCustomerStore'),
		    columns: [
		        { header: 'Name',  dataIndex: 'customerName' },		        
		        { header: 'Phone', dataIndex: 'contactNo' },
		        { header: 'Email', dataIndex: 'emailId', flex: 1 }
		    ],
		    height: 200,
		    width: 400,
		    renderTo: Ext.getBody()
		});
	});
</script>
</head>
<body>
<h1>Title1</h1>
</body>
</html>