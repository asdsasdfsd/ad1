function searchByName() {
	var searchButton = document.getElementById('searchButton');
    searchButton.addEventListener('click', function () {
        var name = document.getElementById('name').value;

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/animes/search?name=' + name, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    var parser = new DOMParser();
                    var doc = parser.parseFromString(xhr.responseText, 'text/html');
                    var tableFragment = doc.querySelector('#commentTable table');
                    var commentTable = document.getElementById('commentTable');
                    commentTable.innerHTML = '';
                    commentTable.appendChild(tableFragment);
                } else {
                    console.error('Error occurred while processing the search request.');
                }
            }
        };
        xhr.send();
    });
}

function resetSearch(){
	var nameInput = document.getElementById('name');
	if(nameInput){
		nameInput.value = '';
	}
}

function openAddAnimeModel() {
    var addWindow = document.getElementById('addWindow');
    var overlay = document.getElementById('overlay');

    addWindow.style.display = 'block';
    overlay.style.display = 'block';
}

function closeAddAnimeModel() {
    var addWindow = document.getElementById('addWindow');
    var overlay = document.getElementById('overlay');

    addWindow.style.display = 'none';
    overlay.style.display = 'none';
}

function openEditAnimeModel() {
    var editWindow = document.getElementById('editWindow');
    var overlay = document.getElementById('overlay');

    editWindow.style.display = 'block';
    overlay.style.display = 'block';
}

function closeEditAnimeModel() {
    var editWindow = document.getElementById('editWindow');
    var overlay = document.getElementById('overlay');

    editWindow.style.display = 'none';
    overlay.style.display = 'none';
}


function openDeleteAnimeModel() {
    var deleteWindow = document.getElementById('deleteWindow');
    var overlay = document.getElementById('overlay');
    
    deleteWindow.style.display = 'block';
    overlay.style.display = 'block';
    
}

function closeDeleteAnimeModel() {
    var deleteWindow = document.getElementById('deleteWindow');
    var overlay = document.getElementById('overlay');

    deleteWindow.style.display = 'none';
    overlay.style.display = 'none';
}

