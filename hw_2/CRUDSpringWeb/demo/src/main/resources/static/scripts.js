// Add event listener to table headers
var tableHeaders = document.querySelectorAll("table thead th");
for (var i = 0; i < tableHeaders.length; i++) {
    tableHeaders[i].addEventListener("click", function() {
        var tableBody = this.parentElement.parentElement.querySelector("tbody");
        var rows = Array.from(tableBody.rows);
        var currentSortColumn = this;
        var isAscending = currentSortColumn.dataset.sortDirection === "asc";

        // Sort rows based on the clicked column
        rows.sort(function(a, b) {
            var cellA = a.cells[currentSortColumn.cellIndex].innerText;
            var cellB = b.cells[currentSortColumn.cellIndex].innerText;

            if (isAscending) {
                return cellA.localeCompare(cellB);
            } else {
                return cellB.localeCompare(cellA);
            }
        });

        // Remove existing rows and insert sorted rows
        tableBody.innerHTML = "";
        for (var j = 0; j < rows.length; j++) {
            tableBody.appendChild(rows[j]);
        }

        // Toggle sort direction
        isAscending = !isAscending;
        currentSortColumn.dataset.sortDirection = isAscending ? "asc" : "desc";
    });
}

// Set initial sort direction
tableHeaders[0].dataset.sortDirection = "asc";