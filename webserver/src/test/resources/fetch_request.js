// GET all
fetch('/group/').then(response => response.json().then(console.log))

// GET one
fetch('/group/2').then(response => response.json().then(console.log))


// POST add new one
fetch(
    '/group',
    {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: 'Fourth group (4)', id: 10 })
    }
).then(result => result.json().then(console.log))
// POST get value
fetch(
    '',
    {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
    }
).then(result => result.json().then(console.log))

// PUT save existing
fetch(
    '/group/4',
    {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text: 'Fourth group', id: 10 })
    }
).then(result => result.json().then(console.log));

// DELETE existing
fetch('/group/4', { method: 'DELETE' }).then(result => console.log(result))