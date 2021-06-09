var category = {
  text: "",
  parent: ""
};

var editedNode = {
  oldName: "",
  newName: ""
};

var add = function () {
  $('#alert-add').addClass('hidden');
  category.parent = selectedNode.text;
  category.text = $('#input-add').val();
  $.ajax({
    type: 'POST',
    url: '/api/category/add',
    contentType: "application/json",
    data: JSON.stringify(category),
    dataType: "json",
    success: function (data) {
      $('#add').addClass('hidden');
      var nodes = Array.of(data);
      $('#treeview').treeview({
        data: nodes,
        onNodeSelected: function (event, node) {
          selectedNode = node;
        }
      });
    },
    error: function (error) {
      $('#alert-add').removeClass('hidden');
    }
  });
};

var remove = function () {
  deleteNode();
  category.text = selectedNode.text;
  $.ajax({
    type: 'DELETE',
    url: '/api/category/delete',
    contentType: "application/json",
    data: JSON.stringify(category),
    dataType: "json",
    success: function (data) {
      var nodes = Array.of(data);
      $('#treeview').treeview({
        data: nodes,
        onNodeSelected: function (event, node) {
          selectedNode = node;
        }
      });
    },
    error: function (error) {
      $('#alert').removeClass('hidden');
    }
  });
};

var edit = function () {
  $('#alert-edit').addClass('hidden');
  editedNode.oldName = selectedNode.text;
  editedNode.newName = $('#input-edit').val();
  $.ajax({
    type: 'PUT',
    url: '/api/category/edit',
    contentType: "application/json",
    data: JSON.stringify(editedNode),
    dataType: "json",
    success: function (data) {
      $('#edit').addClass('hidden');
      var nodes = Array.of(data);
      $('#treeview').treeview({
        data: nodes,
        onNodeSelected: function (event, node) {
          selectedNode = node;
        }
      });
    },
    error: function (error) {
      $('#alert-edit').removeClass('hidden');
    }
  });
};