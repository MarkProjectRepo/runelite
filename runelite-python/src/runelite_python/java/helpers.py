def wrap_getter(wrapper_class):
    """
    A decorator to wrap the return type of a function into a specified class.

    Args:
        wrapper_class (type): The class to wrap the return type with.

    Returns:
        function: The decorated function with wrapped return type.
    """
    def decorator(func):
        def wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            return wrapper_class(result)
        return wrapper
    return decorator

# Wrapper but for iterators
def wrap_iterator(wrapper_class):
    """
    A decorator to wrap the return type of a function that returns an iterator into a specified class.

    Args:
        wrapper_class (type): The class to wrap the return type with.

    Returns:
        function: The decorated function with wrapped return type.
    """
    def decorator(func):
        def wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            if "iterator" in dir(result):
                return [wrapper_class(obj) for obj in result.iterator()]
            return [wrapper_class(obj) for obj in result]
        return wrapper
    return decorator