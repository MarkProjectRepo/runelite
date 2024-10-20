from .npc import NPC
from typing import List
from runelite_python.java.api.scene import Scene
from runelite_python.java.helpers import wrap_getter, wrap_iterator
from runelite_python.java.api.player import Player

class WorldView:
    """
    Represents the world view in the game.

    Methods:
        - get_id
        - is_top_level
        - get_scene
        - players
        - npcs
        - world_entities
        - get_collision_maps
        - get_plane
        - get_tile_heights
        - get_tile_settings
        - get_size_x
        - get_size_y
        - get_base_x
        - get_base_y
        - create_projectile
        - get_projectiles
        - get_graphics_objects
        - get_selected_scene_tile
        - is_instance
        - get_instance_template_chunks

    """
    def __init__(self, worldview_instance):
        self.world_view = worldview_instance

    def get_id(self) -> int:
        return self.world_view.getId()

    def is_top_level(self) -> bool:
        return self.world_view.isTopLevel()

    @wrap_getter(Scene)
    def get_scene(self):
        return self.world_view.getScene()

    @wrap_iterator(Player)
    def players(self):
        return self.world_view.players()

    def npcs(self) -> List[NPC]:
        return [NPC(npc) for npc in self.world_view.npcs().iterator()]

    def world_entities(self):
        return self.world_view.worldEntities()

    def get_collision_maps(self):
        return self.world_view.getCollisionMaps()

    def get_plane(self) -> int:
        return self.world_view.getPlane()

    def get_tile_heights(self):
        return self.world_view.getTileHeights()

    def get_tile_settings(self):
        return self.world_view.getTileSettings()

    def get_size_x(self) -> int:
        return self.world_view.getSizeX()

    def get_size_y(self) -> int:
        return self.world_view.getSizeY()

    def get_base_x(self) -> int:
        return self.world_view.getBaseX()

    def get_base_y(self) -> int:
        return self.world_view.getBaseY()

    def create_projectile(self, id, plane, startX, startY, startZ, startCycle, endCycle, slope, startHeight, endHeight, target, targetX, targetY):
        return self.world_view.createProjectile(id, plane, startX, startY, startZ, startCycle, endCycle, slope, startHeight, endHeight, target, targetX, targetY)

    def get_projectiles(self):
        return self.world_view.getProjectiles()

    def get_graphics_objects(self):
        return self.world_view.getGraphicsObjects()

    def get_selected_scene_tile(self):
        return self.world_view.getSelectedSceneTile()

    def is_instance(self) -> bool:
        return self.world_view.isInstance()

    def get_instance_template_chunks(self):
        return self.world_view.getInstanceTemplateChunks()