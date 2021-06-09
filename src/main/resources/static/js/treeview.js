var selectedNode = {};

$(document).ready(function () {
  $.ajax({
    type: 'GET',
    url: '/api/category',
    dataType: "json",
    success: function (data) {
      $('#btn-clear-search').removeClass('hidden');
      $('#input-search').removeClass('hidden');
      $('#btn-search').removeClass('hidden');

      var nodes = Array.of(data);
      $('#treeview').treeview({
        data: nodes,
        showTags: true,
        onNodeSelected: function (event, node) {
          selectedNode = node;
          $('#input-edit').val(selectedNode.text);
        }
      });
    },
    error: function (error) {
      $('#alert').removeClass('hidden');
    }
  });
});

$('#input-add').click(function () {
  $('#alert-add').addClass('hidden');
});

$('#input-edit').click(function () {
  $('#alert-edit').addClass('hidden');
});

