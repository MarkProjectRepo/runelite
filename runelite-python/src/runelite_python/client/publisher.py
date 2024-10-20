from abc import ABC, abstractmethod

class Publisher(ABC):
    def __init__(self):
        self._subscribers = set()

    def add_subscriber(self, subscriber):
        """Add a subscriber."""
        self._subscribers.add(subscriber)

    def remove_subscriber(self, subscriber):
        """Remove a subscriber."""
        self._subscribers.discard(subscriber)
    
    @abstractmethod
    def publish(self):
        pass