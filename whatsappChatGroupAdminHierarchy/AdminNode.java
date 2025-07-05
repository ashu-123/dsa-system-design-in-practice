package whatsappChatGroupAdminHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdminNode {

    private String adminName;

    private List<AdminNode> subAdmins = new ArrayList<>();

    private AdminNode parentAdmin;

    public String getAdminName() {
        return adminName;
    }

    public AdminNode setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }

    public List<AdminNode> getSubAdmins() {
        return subAdmins;
    }

    public AdminNode setSubAdmins(List<AdminNode> subAdmins) {
        this.subAdmins = subAdmins;
        return this;
    }

    public AdminNode getParentAdmin() {
        return parentAdmin;
    }

    public AdminNode setParentAdmin(AdminNode parentAdmin) {
        this.parentAdmin = parentAdmin;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminNode adminNode = (AdminNode) o;
        return Objects.equals(adminName, adminNode.adminName) && Objects.equals(subAdmins, adminNode.subAdmins) && Objects.equals(parentAdmin, adminNode.parentAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminName, subAdmins, parentAdmin);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdminNode{");
        sb.append("adminName='").append(adminName).append('\'');
        sb.append(", subAdmins=").append(subAdmins);
        sb.append(", parentAdmin=").append(parentAdmin);
        sb.append('}');
        return sb.toString();
    }
}
