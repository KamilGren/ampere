function tryDeleteCurrentUrl(successfulRedirect="/") {
    const confirmation = confirm();
    if (confirmation !== true) {
        return;
    }
    $.ajax({
        url: window.location.href,
        type: 'DELETE',
        success: function(result) {
            window.location.href = successfulRedirect;
        },
        error: function(xhr, status, error) {
            alert("Failed to delete!");
        }
    });
}
