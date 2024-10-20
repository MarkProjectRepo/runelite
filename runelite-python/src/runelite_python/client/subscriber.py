from abc import ABC, abstractmethod

class Subscriber(ABC):
    @abstractmethod
    def update(self, message):
        """Receive update with a message."""
        pass

    