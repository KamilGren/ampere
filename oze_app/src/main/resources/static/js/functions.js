function tryDeleteCurrentUrl(successfulRedirect="/") {
    const confirmation = confirm("Are you sure you want to delete this?");
    if (confirmation !== true) {
        return;
    }
    $.ajax({
        url: window.location.href,
        type: 'DELETE',
        success: function(result) {
            alert("Successfully deleted!");
            window.location.href = successfulRedirect;
        },
        error: function(xhr, status, error) {
            alert("Failed to delete!");
        }
    });
}
