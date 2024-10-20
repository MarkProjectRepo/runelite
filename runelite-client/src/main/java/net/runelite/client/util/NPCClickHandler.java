// package net.runelite.client.util;

// import net.runelite.api.NPC;
// import net.runelite.api.WorldView;
// import java.awt.event.MouseEvent;

// public class NPCClickHandler {

//     private final WorldView worldView;
//     public NPCClickHandler(WorldView worldView) {
//         this.worldView = worldView;
//     }

//     public void handleMouseEvent(MouseEvent e) {
//         int mouseX = e.getX();
//         int mouseY = e.getY();
//         boolean clickedOnObject = false;

//         // Check for NPCs in the scene
//         for (NPC npc : worldView.npcs()) {
//             if (npc.getConvexHull().contains(mouseX, mouseY)) {
//                 // NPC was clicked
//                 System.out.println("Clicked NPC: " + npc.getName());
//                 clickedOnObject = true;
//             }
//         }

//         // Check for other objects in the scene (e.g., GroundObjects, GameObjects, etc.)
//         // if (!clickedOnObject) {
//         //     // Add logic to check for other objects if needed
//         //     // For now, we assume no other objects are clicked
//         // }

//         if (!clickedOnObject) {
//             // No object was clicked
//             System.out.println("Clicked on no NPCs");
//         }
//     }
// }