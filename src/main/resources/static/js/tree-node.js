var selectors = {
  'tree': '#treeview',
  'input': '#input-search',
  'reset': '#btn-clear-search'
};

var reset = function(tree) {
  tree.collapseAll();
  tree.enableAll();
};

var clearTree = function() {
  $(selectors.input).val('');
  var tree = $(selectors.tree).treeview(true);
  reset(tree);
  tree.clearSearch();
};

var createNode = function () {
  $('#add').removeClass('hidden');
  $('#edit').addClass('hidden');
};


var editNode = function () {
  if (selectedNode) {
    $('#add').addClass('hidden');
    $('#edit').removeClass('hidden');
  }
};

var deleteNode = function () {
  $('#add').addClass('hidden');
  $('#edit').addClass('hidden');

};

var search = function() {
  var pattern = $(selectors.input).val();
  var tree = $(selectors.tree).treeview(true);
  reset(tree);
  tree.search(pattern);
};