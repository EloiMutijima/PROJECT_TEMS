<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<% 
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    List<Map<String, String>> modules = new ArrayList<>();
    
    try {
        // Database connection setup
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "password");

        // Query to retrieve modules from the 'courses' table
        String sql = "SELECT Module_code, Module_name, Department, Program, Level, Trainer_id FROM courses";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Map<String, String> module = new HashMap<>();
            module.put("Module_code", rs.getString("Module_code"));
            module.put("Module_name", rs.getString("Module_name"));
            module.put("Department", rs.getString("Department"));
            module.put("Program", rs.getString("Program"));
            module.put("Level", rs.getString("Level"));
            module.put("Trainer_id", rs.getString("Trainer_id"));
            modules.add(module);
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>

<!-- HTML Form -->
<form action="SubmitEvaluationServlet" method="post">
    <label for="module">Select Module:</label>
    <select id="module" name="Module_code" onchange="autoFillModuleDetails()">
        <option value="">--Select Module--</option>
        <% for (Map<String, String> module : modules) { %>
            <option value="<%= module.get("Module_code") %>">
                <%= module.get("Module_name") %>
            </option>
        <% } %>
    </select>

    <!-- Auto-Fill Fields -->
    <div>
        <label for="module_code">Module Code:</label>
        <input type="text" id="module_code" name="Module_code" readonly>

        <label for="module_name">Module Name:</label>
        <input type="text" id="module_name" name="Module_name" readonly>

        <label for="department">Department:</label>
        <input type="text" id="department" name="Department" readonly>

        <label for="program">Option:</label>
        <input type="text" id="program" name="Option" readonly>

        <label for="trainer_id">Trainer ID:</label>
        <input type="text" id="trainer_id" name="Trainer_id" readonly>
    </div>

    <!-- Evaluation Criteria -->
    <table>
        <!-- The rest of your evaluation criteria goes here (as per your provided HTML) -->
        <thead>
            <tr>
                <th>S/N</th>
                <th>Learning Environment</th>
                <th colspan="5" class="rating">Give marks (Use radio buttons)</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Classroom setting</td>
                <td class="rating"><input type="radio" name="classroom_setting" value="1"></td>
                <td class="rating"><input type="radio" name="classroom_setting" value="2"></td>
                <td class="rating"><input type="radio" name="classroom_setting" value="3"></td>
                <td class="rating"><input type="radio" name="classroom_setting" value="4"></td>
                <td class="rating"><input type="radio" name="classroom_setting" value="5"></td>
            </tr>
            <!-- Include the rest of your criteria here -->
        </tbody>
    </table>

    <p><button type="submit">Submit Feedback</button></p>
</form>

<script>
    const modules = <%= modules.toString() %>;

    function autoFillModuleDetails() {
        const selectedModuleCode = document.getElementById("module").value;
        const selectedModule = modules.find(module => module.Module_code === selectedModuleCode);

        if (selectedModule) {
            document.getElementById("module_code").value = selectedModule.Module_code;
            document.getElementById("module_name").value = selectedModule.Module_name;
            document.getElementById("department").value = selectedModule.Department;
            document.getElementById("program").value = selectedModule.Program;
            document.getElementById("trainer_id").value = selectedModule.Trainer_id;
        }
    }
</script>
