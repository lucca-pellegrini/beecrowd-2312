// Beecrowd 2479

#include <stdio.h>
#include <stdlib.h>

#define MAX_NAME_LEN 20

void insertion_sort(char **vec, int n);
int strcmp2(char *a, char *b);

int main(void)
{
	int n;
	int comportadas = 0;
	char **vec;

	scanf("%d ", &n);
	vec = (char **)malloc(n * sizeof(char *));

	for (int i = 0; i < n; ++i) {
		char c;
		vec[i] = (char *)malloc((MAX_NAME_LEN + 1) * sizeof(char));

		scanf("%c ", &c);
		if (c == '+')
			++comportadas;

		scanf("%s ", vec[i]);
	}

	insertion_sort(vec, n);

	for (int i = 0; i < n; ++i) {
		puts(vec[i]);
		free(vec[i]);
	}
	free(vec);

	printf("\nSe comportaram: %d | Nao se comportaram: %d\n", comportadas,
	       n - comportadas);

	return 0;
}

void insertion_sort(char **vec, int n)
{
	for (int i = 1; i < n; ++i) {
		char *tmp = vec[i];
		int j;
		for (j = i - 1; j >= 0 && strcmp2(tmp, vec[j]) < 0; --j)
			vec[j + 1] = vec[j];
		vec[j + 1] = tmp;
	}
}

int strcmp2(char *a, char *b)
{
	int resp = 0;
	a--;
	b--;

	while (!resp && *++a && *++b) {
		if (*a > *b)
			resp = 1;
		else if (*b > *a)
			resp = -1;
	}

	if (!resp && *a)
		resp = 1;
	else if (!resp && *b)
		resp = -1;

	return resp;
}
