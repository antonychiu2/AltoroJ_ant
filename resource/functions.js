function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function adjustIFrameSize(id) {
   var myIFrame = document.getElementById(id);
   if (myIFrame) {
       if (myIFrame.contentDocument &&
           myIFrame.contentDocument.body.offsetHeight) {
	  // W3C DOM syntax for NN 6
          myIFrame.height = myIFrame.contentDocument.body.offsetHeight;
       } else if (myIFrame.Document && myIFrame.Document.body.scrollHeight) {
          // IE DOM syntax
          myIFrame.height = myIFrame.Document.body.scrollHeight;
       }
   }
}

//
// Moves selected items from the src to the destination
// @param src is the src select control
// @param dest is the dest select control;
//
function moveSelectedItems(src, dest) {
   moveItems(src, dest, true);
}

// Move all items from the src to the destination
// @param src is the src select control
// @param dest is the dest select control
//
function moveAllItems(src, dest) {
   moveItems(src, dest, false);
}

//
// Moves items from the src to the destination
// @param src is the src select control
// @param dest is the dest select control
// @param selectedOnly is true if we only want selected (default true)
//
function moveItems(src, dest, selectedOnly) {
    var i;

    if (selectedOnly == null) {
        selectedOnly = true;
    }

    var index = 0;
    deselectAllItems(dest);
    while (index < src.options.length) {
        var thisitem = src.options[index];
        if ((selectedOnly == false || thisitem.selected == true) && thisitem.text != '') {
            appendNewItem(dest, thisitem.text, thisitem.value, true);
            src.options[index] = null;
        } else {
            index++;
        }
    }
}

//
// Create an item in the list and append it to the end
// @param list the list to append the new item
// @param text the text of the new item
// @param value the value of the new item
//
function appendNewItem(list, text, value, sel) {
    var option = new Option(text, value, false, false)
    list.options[list.options.length] = option;
    if (sel) {
        option.selected = sel;
    }
    return option;
}

//
// Move all the selected items down one spot in the list
// @param src is the src select control
//
function moveSelectedItemsDown(src) {
   var i;
   for (i = src.options.length - 1; i >= 0; --i) {
       var thisitem = src.options[i];
       if (thisitem.selected == true) {
          // already at the end
          if (i == src.options.length - 1) {
              return;
          } else {
              // move the item down
              var nextItem = src.options[i + 1];

              thisoption = new Option(thisitem.text, thisitem.value, false, false);
              swapoption = new Option(nextItem.text, nextItem.value, false, false);
              src.options[i] = swapoption;
              src.options[i + 1] = thisoption;
              thisoption.selected = true;
          }
       }
   }
}

//
// Move all the selected items down one spot in the list
// @param src is the src select control
//
function moveSelectedItemsUp(src) {
   var i;
   for (i = 0; i < src.options.length; ++i) {
       var thisitem = src.options[i];
       if (thisitem.selected == true) {
          // already at the beginning
          if (i == 0) {
              return;
          } else {
              // move the item down
              var prevItem = src.options[i - 1];

              thisoption = new Option(thisitem.text, thisitem.value, false, false);
              swapoption = new Option(prevItem.text, prevItem.value, false, false);
              src.options[i] = swapoption;
              src.options[i - 1] = thisoption;
              thisoption.selected = true;
          }
       }
   }
}

//
// Copy all selected items from src to dest, if they are not already in dest
// @param src the list control to copy from
// @param dest the list control to copy to
//
function copySelectedItems(src, dest) {
    var i;

    deselectAllItems(dest);
    for (i = 0; i < src.options.length; ++i) {
        var thisoption = src.options[i];
        if (thisoption.selected == true) {
            var option = findItemByValue(dest, thisoption.value);
            if (option != null) {
                option.selected = true;
            } else {
                appendNewItem(dest, thisoption.text, thisoption.value, true);
            }
        }
    }
}

//
// A function that determines if a list contains an option of a certain value
// @param list the list
// @param value the value to look for
// @return true if the list contains the value, else false
//
function doesListContain(list, value) {
    return findItemByValue(list, value) == null;
}

//
// Find an item by value
// @param value the value to find
//
function findItemByValue(list, value) {
    var i;
    for (i = 0; i < list.options.length; ++i) {
        if (list.options[i].value == value) {
            return list.options[i];
        }
    }
    return null;
}

//
// Remove an item by value
// @param list the list to remove the item from
// @param value the value to remove
// @return true if found the value
//
function removeItemByValue(list, value) {
    for (i = 0; i < list.options.length; ++i) {
        if (list.options[i].value == value) {
            list.options[i].value = null;
            return true;
        }
    }
    return false;
}

//
// Remove the selected items from the list
// @param list the list to remove the items from
//
function removeSelectedItems(list) {
    var i;
    for (i = list.options.length - 1; i >= 0; --i) {
        if (list.options[i].selected == true) {
            list.options[i] = null;
        }
    }
}

//
// Remove all the items from the list
// @param list the list to remove the items from
//
function removeAllItems(list) {
    var i;
    for (i = list.options.length - 1; i >= 0; --i) {
        list.options[i] = null;
    }
}

//
// Selects all the items in the list control
// @param list the list control to select everything in
//
function selectAllItems(src) {
    var i;
    for (i = 0; i < src.options.length; ++i) {
        src.options[i].selected = true;
    }
}

//
// Deselects all the items in the list control
// @param list the list control to deselect everything in
//
function deselectAllItems(src) {
    var i;
    for (i = 0; i < src.options.length; ++i) {
        src.options[i].selected = false;
    }
}

//
// Get selected item (assumes only one)
// @param list the list control to look in
//
function findSelectedItem(list) {
    var i = 0;
    var sel = null;
    for (i = 0; sel == null && i < list.options.length; ++i) {
        if (list.options[i].selected == true) {
           sel = list.options[i];
        }
    }
    return sel;
}

//
// Expand or collapse an item in a tree structure
// @param id the id of the item to expand
// @param expanded if this is passed in, specifies the image to use for an expanded node
// @param collapsed if this is passed in, specifies the image to use for a collapsed node
// @param forceExpand if true, forces the tree to expand
//
function expandOrCollapse(id, expanded, collapsed, forceExpand) {
    expandOrCollapseDiv('div_' + id, 'img_' + id, expanded, collapsed, forceExpand);
}

//
// Expand all the divs in the document
// @param start_id deprecated
// @param expanded if this is passed in, specifies the image to use for an expanded node
// @param collapsed if this is passed in, specifies the image to use for a collapsed node
// @param forceExpand if true, forces the tree to expand
function expandOrCollapseAll(start_id, expanded, collapsed, forceExpand) {
    var divs = document.getElementsByTagName("div");
    for (var i = 0; i < divs.length; ++i) {
        var div = divs[i];
        if (div.id.indexOf("div_") == 0) {
            var imgId = "img_" + div.id.substring(4);
            expandOrCollapseDiv(div.id, imgId, expanded, collapsed, forceExpand);
        }
    }
}

//
// Expand or collapse a div
// @param divId the id of the item to expand
// @Param imgId the id of the image to switch out
// @param expanded if this is passed in, specifies the image to use for an expanded node
// @param collapsed if this is passed in, specifies the image to use for a collapsed node
// @param forceExpand if true, forces the tree to expand
//
function expandOrCollapseDiv(divId, imgId, expanded, collapsed, forceExpand) {
    var elt = document.getElementById(divId);
    var image = document.getElementById(imgId);
    if (!expanded) {
        expanded = '../images/tree-expanded.gif';
    }
    if (!collapsed) {
        collapsed = '../images/tree-collapsed.gif';
    }

    if (elt && image) {
      if (elt.className == 'hidden' || forceExpand) {
        elt.className = 'visible';
        image.src = expanded;
      }
      else {
        elt.className = 'hidden';
        image.src = collapsed;
      }
    }
}

//
// Expand an entire path, splits the string into subparts
// @param path the path of the item to expand
// @param expanded if this is passed in, specifies the image to use for an expanded node
// @param collapsed if this is passed in, specifies the image to use for a collapsed node
//
function expandPath(id, expanded, collapsed) {
   var parts = id.split('-');
   if (parts) {
     var part = '';
     var i;
     for (i = 0; i < parts.length; ++i) {
       part += parts[i];
       expandOrCollapse(part, expanded, collapsed, true);
       part += '-';
     }
   }
}

var plusIcon = 'images/foldingPlus.gif';
var minusIcon = 'images/foldingMinus.gif';
