package whatsappChatGroupAdminHierarchy;

import java.util.*;

public class GroupAdminTree {

    private final AdminNode rootAdmin;

    private final Map<String, AdminNode> adminNodes = new HashMap<>();

    public GroupAdminTree(String creatorName) {
        this.rootAdmin = new AdminNode()
                .setAdminName(creatorName)
                .setParentAdmin(null);
        adminNodes.put(creatorName, rootAdmin);
    }

    public boolean addAdmin(String promoterId, String newAdminId) {
        if (!adminNodes.containsKey(promoterId)) {
            System.out.println("Promoter not found: " + promoterId);
            return false;
        }
        if (adminNodes.containsKey(newAdminId)) {
            System.out.println("Admin already exists: " + newAdminId);
            return false;
        }

        AdminNode promoter = adminNodes.get(promoterId);
        AdminNode newAdmin = new AdminNode()
                .setAdminName(newAdminId)
                .setParentAdmin(promoter);

        promoter.getSubAdmins().add(newAdmin);
        adminNodes.put(newAdminId, newAdmin);
        return true;
    }

    public boolean canRemove(String removerId, String targetId) {
        if (!adminNodes.containsKey(targetId)) {
            System.out.println("Target id not found: " + targetId);
            return false;
        }

        if (!adminNodes.containsKey(removerId)) {
            System.out.println("Remover id not found: " + removerId);
            return false;
        }

        AdminNode target = adminNodes.get(targetId);
        AdminNode remover = adminNodes.get(removerId);

        AdminNode parent = target.getParentAdmin();
        while (parent != null) {
            if (parent.equals(remover)) {
                return true;
            }
            parent = parent.getParentAdmin();
        }

        return false;
    }

    public AdminNode removeAdmin(String removerId, String targetAdminId) {
        if(this.canRemove(removerId, targetAdminId)) {
            AdminNode target = adminNodes.get(targetAdminId);
            AdminNode parent = target.getParentAdmin();
            parent.getSubAdmins().remove(target);
            target.setParentAdmin(null);
            adminNodes.remove(targetAdminId);
            return target;
        }

        throw new IllegalStateException("Removal Unsuccessful. Please retry again");
    }

    public void getHierarchy() {
        // Level Order Traversal

        Queue<AdminNode> adminsByLevel = new LinkedList<>();
        adminsByLevel.add(rootAdmin);

        int level = 1;
        while (!adminsByLevel.isEmpty()) {
            int size = adminsByLevel.size();
            System.out.println("Admins at level " + level);
            for(int i=0;i<size;i++) {
                AdminNode adminNode = adminsByLevel.poll();
                System.out.print(adminNode.getAdminName() + " ");

                adminsByLevel.addAll(adminNode.getSubAdmins());
            }
            level++;
            System.out.println();
        }
    }

    public List<AdminNode> getSubAdmins(String adminId) {
        if (!adminNodes.containsKey(adminId)) {
            System.out.println("Target id not found: " + adminId);
            throw new IllegalArgumentException("No admin found with the given admin id" + adminId);
        }

        return adminNodes.get(adminId).getSubAdmins();
    }

    public void getAllAdminsUnder(String adminId) {
        if (!adminNodes.containsKey(adminId)) {
            System.out.println("Target id not found: " + adminId);
            throw new IllegalArgumentException("No admin found with the given admin id" + adminId);
        }

        AdminNode adminNode = adminNodes.get(adminId);
        dfs(adminNode, "");
    }

    private void dfs(AdminNode adminNode, String indent) {
        System.out.println(indent + adminNode.getAdminName());
        for (AdminNode subAdmin : adminNode.getSubAdmins()) {
            dfs(subAdmin, indent + "  ");
        }
    }
}

