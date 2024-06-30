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

const POLISH_FORMATTER = new Intl.NumberFormat('pl-PL', {
    style: 'currency',
    currency: 'PLN',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
});

function formatPrice(price) {
    return POLISH_FORMATTER.format(price);
}

function hideModal(selector) {
    $(selector).modal('hide');
}
