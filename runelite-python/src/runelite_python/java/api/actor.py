from runelite_python.java.api.coord.localpoint import LocalPoint
from runelite_python.java.helpers import wrap_getter
from runelite_python.java.api.coord.worldpoint import WorldPoint

class Actor:
    """
    Represents a RuneScape actor/entity.
    """

    def __init__(self, actor_instance):
        self.actor_instance = actor_instance

    def get_world_view(self):
        """
        Get the WorldView this actor belongs to.
        """
        return self.actor_instance.getWorldView()

    def get_combat_level(self) -> int:
        """
        Gets the combat level of the actor.
        
        Returns:
            int: the combat level
        """
        return self.actor_instance.getCombatLevel()

    def get_name(self) -> str:
        """
        Gets the name of the actor.
        
        Returns:
            str: the name of the actor, or None if not available
        """
        return self.actor_instance.getName()

    def is_interacting(self) -> bool:
        """
        Determines if the actor is interacting with another actor.
        
        Returns:
            bool: True if interacting, False otherwise
        """
        return self.actor_instance.isInteracting()

    def get_interacting(self):
        """
        Gets the actor being interacted with.
        
        Returns:
            Actor: the actor being interacted with, or None if no interaction is occurring
        """
        return self.actor_instance.getInteracting()

    def get_health_ratio(self) -> int:
        """
        Gets the health of the actor in health scale units.
        
        Returns:
            int: the health ratio, or -1 if not available
        """
        return self.actor_instance.getHealthRatio()

    def get_health_scale(self) -> int:
        """
        Gets the maximum value the health ratio can return.
        
        Returns:
            int: the health scale, or -1 if not available
        """
        return self.actor_instance.getHealthScale()

    def get_world_location(self) -> WorldPoint:
        """
        Gets the server-side location of the actor.
        
        Returns:
            WorldPoint: the server location
        """
        return self.actor_instance.getWorldLocation()

    @wrap_getter(LocalPoint)
    def get_local_location(self) -> LocalPoint:
        """
        Gets the client-side location of the actor.
        
        Returns:
            LocalPoint: the client location
        """
        return self.actor_instance.getLocalLocation()

    def get_orientation(self) -> int:
        """
        Gets the target orientation of the actor.
        
        Returns:
            int: the orientation
        """
        return self.actor_instance.getOrientation()

    def get_current_orientation(self) -> int:
        """
        Gets the current orientation of the actor.
        
        Returns:
            int: the current orientation
        """
        return self.actor_instance.getCurrentOrientation()

    def get_animation(self) -> int:
        """
        Gets the current animation the actor is performing.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getAnimation()

    def set_animation(self, animation: int):
        """
        Sets an animation for the actor to perform.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setAnimation(animation)

    def get_pose_animation(self) -> int:
        """
        Gets the secondary animation the actor is performing.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getPoseAnimation()

    def set_pose_animation(self, animation: int):
        """
        Set the idle pose animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setPoseAnimation(animation)

    def get_idle_pose_animation(self) -> int:
        """
        Get the idle pose animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getIdlePoseAnimation()

    def set_idle_pose_animation(self, animation: int):
        """
        Set the idle pose animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setIdlePoseAnimation(animation)

    def get_idle_rotate_left(self) -> int:
        """
        Get the idle rotate left animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getIdleRotateLeft()
    
    def set_idle_rotate_left(self, animation: int):
        """
        Set the idle rotate left animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setIdleRotateLeft(animation)
    
    def get_idle_rotate_right(self) -> int:
        """
        Get the idle rotate right animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getIdleRotateRight()
    
    def set_idle_rotate_right(self, animation: int):
        """
        Set the idle rotate right animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setIdleRotateRight(animation)
    
    def get_walk_animation(self) -> int:
        """
        Get the walk animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getWalkAnimation()
    
    def set_walk_animation(self, animation: int):
        """
        Set the walk animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setWalkAnimation(animation)

    def get_walk_rotate_left(self) -> int:
        """
        Get the walk rotate left animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getWalkRotateLeft()
    
    def set_walk_rotate_left(self, animation: int):
        """
        Set the walk rotate left animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setWalkRotateLeft(animation)
    
    def get_walk_rotate_right(self) -> int:
        """
        Get the walk rotate right animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getWalkRotateRight()
    
    def set_walk_rotate_right(self, animation: int):
        """
        Set the walk rotate right animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setWalkRotateRight(animation)
    
    
    def get_walk_rotate_180(self) -> int:
        """
        Get the walk rotate 180 animation.
        
        Returns:
            int: the animation ID
        """
        return self.actor_instance.getWalkRotate180()
    
    def set_walk_rotate_180(self, animation: int):
        """
        Set the walk rotate 180 animation.
        
        Args:
            animation (int): the animation ID
        """
        self.actor_instance.setWalkRotate180(animation)
    
    def get_animation_frame(self) -> int:
        """
        Get the frame of the animation the actor is performing.
        
        Returns:
            int: the animation frame
        """
        return self.actor_instance.getAnimationFrame()
    
    def set_animation_frame(self, frame: int):
        """
        Set the frame of the animation the actor is performing.
        
        Args:
            frame (int): the animation frame
        """
        self.actor_instance.setAnimationFrame(frame)
    
    def get_convex_hull(self):
        """
        Get the convex hull of the actor.
        
        Returns:
            Shape: the convex hull
        """
        return self.actor_instance.getConvexHull()
    
    def get_world_area(self):
        """
        Get the world area of the actor.
        
        Returns:
            WorldArea: the world area
        """
        return self.actor_instance.getWorldArea()
    
    def get_overhead_text(self) -> str:
        """
        Get the overhead text of the actor.
        
        Returns:
            str: the overhead text
        """
        return self.actor_instance.getOverheadText()
    
    def is_dead(self) -> bool:
        """
        Checks if the actor is dead.
        
        Returns:
            bool: True if dead, False otherwise
        """
        return self.actor_instance.isDead()