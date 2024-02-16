function changeStyle(isMouseOver) {
    var registerText = document.getElementById('registerText');
    if (isMouseOver) {
        registerText.style.color = '#2E89EA';
        registerText.style.textDecoration = 'underline';
    } else {
        registerText.style.color = ''; // Use an empty string to remove inline style
        registerText.style.textDecoration = '';
    }
}

function registerWindow1(event) {
	event.preventDefault(); // 阻止表单默认提交
	document.getElementById('registerWindow1').style.display = "block";
	document.getElementById('registerWindow2').style.display = "none";
	document.getElementById('registerWindow3').style.display = "none";
	document.getElementById('overlay').style.display = "block";
}

function registerWindow2(event) {
	event.preventDefault(); // 阻止表单默认提交
	document.getElementById('registerWindow1').style.display = "none";
	document.getElementById('registerWindow2').style.display = "block";
	document.getElementById('registerWindow3').style.display = "none";
	document.getElementById('overlay').style.display = "block";
}

function registerWindow3(event) {
	event.preventDefault(); // 阻止表单默认提交
	document.getElementById('registerWindow1').style.display = "none";
	document.getElementById('registerWindow2').style.display = "none";
	document.getElementById('registerWindow3').style.display = "block";
	document.getElementById('overlay').style.display = "block";
}

function cancel() {
	document.getElementById('registerWindow1').style.display = "none";
	document.getElementById('registerWindow2').style.display = "none";
	document.getElementById('registerWindow3').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function toggleTag(event, sourceContainerId, targetContainerId) {
    const clickedTag = event.target.closest('.tag');
    if (clickedTag) {
        const sourceContainer = document.getElementById(sourceContainerId);
        const targetContainer = document.getElementById(targetContainerId);

        sourceContainer.removeChild(clickedTag);
        targetContainer.appendChild(clickedTag);

        toggleSelected(clickedTag);
    }
}

function toggleSelected(tag) {
    tag.classList.toggle('selectedTag');

    const tagActions = tag.querySelector('.tagActions');

    // 切换符号，如果当前是 '+'，则切换为 '×'，反之亦然
    tagActions.innerText = tag.classList.contains('selectedTag') ? '×' : '+';
}

