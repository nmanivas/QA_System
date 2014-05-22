CommonUtil = function()
{
};

CommonUtil.createChild = function(type, className, attributes, values)
{
    var element = document.createElement(type);
    if(className != "")
		element.className = className;
    for(var i=0;i<attributes.length;i++)
    {
        element.setAttribute(attributes[i], values[i]);
    }
    return element;
};

CommonUtil.setAttributes = function(element, className, attributes, values)
{
    if(className != "")
    	element.className = className;
    for(var i=0;i<attributes.length;i++)
    {
        element.setAttribute(attributes[i], values[i]);
    }
};
